/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import org.lwjgl.input.Keyboard;

/**
 * Actual game logic. Differs by current application
 * @author Honza
 */
public class Game {
    
    private Mesh mesh;
    private Shader shader;
    private Transform transform;
    private Camera camera;
    private Material material;
    
    private InputControl inputControl;
    
    public Matrix4f matrix = new Matrix4f();
    
    
    float temp = 0.0f; 
    
    public Game() {
        mesh = new Mesh();//mesh = ResourceLoader.loadMesh("box.obj");
        shader = new PhongShader();
        camera = new Camera();
        material = new Material(ResourceLoader.loadTexture("checkerboardBlue.png"), new Vector3f(1, 1, 1));
        
        inputControl = new InputControl();
        
        //Sets ambient light value
        PhongShader.setAmbientLight(new Vector3f(0.1f, 0.1f, 0.1f));
        //Sets directional light value
        PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1, 1, 1), 0.8f), new Vector3f(1, 1, 1)));
        
        Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.2f, 1000);
        Transform.setCamera(camera);
        transform = new Transform();
       
        /*Vertex[] data = new Vertex[]{
                new Vertex(new Vector3f(1.000000f, -1.000000f, -1.000000f)),
                new Vertex(new Vector3f(1.000000f, -1.000000f, 1.000000f)),
                new Vertex(new Vector3f(-1.000000f, -1.000000f, 1.000000f)),
                new Vertex(new Vector3f(-1.000000f, -1.000000f, -1.000000f)),
                new Vertex(new Vector3f(1.000000f, 1.000000f, -1.000000f)),
                new Vertex(new Vector3f(1.000000f, 1.000000f, 1.000000f)),
                new Vertex(new Vector3f(-1.000000f, 1.000000f, 1.000000f)),
                new Vertex(new Vector3f(-1.000000f, 1.000000f, -1.000000f))
                new Vertex(new Vector3f(1, 0, -1)),
                new Vertex(new Vector3f(1, 0, 1)),
                new Vertex(new Vector3f(-1, 0, 1)),
                new Vertex(new Vector3f(-1, 0, -1)),};

        int[] indices = new int[]{1, 2, 3,
                7, 6, 5,
                4, 5, 1,
                5, 6, 2,
                2, 6, 7,
                0, 3, 7,
                0, 1, 3,
                4, 7, 5,
                0, 4, 1,
                1, 5, 2,
                3, 2, 7,
                4, 0, 7};
        
        
        mesh.addVerticies(data, indices);*/
        
        Vertex[] data = new Vertex[] {
            new Vertex(new Vector3f(-1, -1, 0), new Vector2f(0, 0)),
            new Vertex(new Vector3f(0, 1, 0), new Vector2f(0.5f, 0)),
            new Vertex(new Vector3f(1, -1, 0), new Vector2f(1.0f, 0)),
            new Vertex(new Vector3f(0, -1, 1), new Vector2f(0, 0.5f))
        };
        
        int[] indices = new int[]{
            0, 1, 3,
            3, 1, 2,
            2, 1, 0,
            0, 2, 3
        };
        
        mesh.addVerticies(data, indices, true);
        
        //shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
        //shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
        //shader.compileShader();
        
        //shader.addUniform("transform");
        
    }
    
    /**
     * Handles input keys/mousebuttons
     */
    public void input() {
        /*if(Input.getKeyDown(Keyboard.KEY_UP))
            System.out.println("We just pressed up!");
        if(Input.getKeyUp(Keyboard.KEY_UP))
            System.out.println("We just released up!");
        
        if(Input.getMouseDown(1))
            System.out.println("We just pressed mouse button 1 (right button by default) at " + Input.getMousePosition() + "!");
        if(Input.getMouseUp(1))
            System.out.println("We just released mouse button 1 (rigth button by default) at " + Input.getMousePosition() + "!");*/
        
        camera.input();
        inputControl.input();
        
        
    }
    
    /**
     * Updating game logic
     */
    public void update() {
        
        temp += Time.getDelta();
        
        float sinTemp = ((float)Math.sin(temp) * 0.5f);
        
        transform.setTranslation(sinTemp, 0, 2);
        transform.setRotation(sinTemp * 180, sinTemp * 180, 0);
        //transform.setScale(0.5f, 0.5f, 0.5f);

    }
    
    
    /**
     * Calls rendering methods and binds shaders
     */
    public void render() {
        
        
        shader.bind();
        shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
        mesh.draw();
        
    }
    
}
