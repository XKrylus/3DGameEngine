/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

/**
 *
 * @author Honza
 */
public class PhongShader extends Shader {
    
    private static Vector3f ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);
    private static DirectionalLight directionalLight = new DirectionalLight(new BaseLight(new Vector3f(1, 1, 1), 0), new Vector3f(0, 0, 0));
    
    public PhongShader() {
        super();
        
        addVertexShader(ResourceLoader.loadShader("phongVertex.vs"));
        addFragmentShader(ResourceLoader.loadShader("phongFragment.fs"));
        compileShader();
        
        addUniform("transform");
        addUniform("transformProjected");
        addUniform("baseColor");
        addUniform("ambientLight");
        
        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
    }
    
    /**
     * Updates all Phong Shader uniforms
     * @param worldMatrix
     * @param projectedMatrix
     * @param material 
     */
    @Override
    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
        
        if(material.getTexture() != null) {
            material.getTexture().bind();
        }
        else {
            RenderUtil.unbindTextures();
        }
        
        setUniform("transformProjected", projectedMatrix);
        setUniform("transform", worldMatrix);
        setUniform("baseColor", material.getColor());
        setUniform("ambientLight", PhongShader.getAmbientLight());
        setUniform("directionalLight", directionalLight);
    }
    
    /**
     * @return the ambientLight
     */
    public static Vector3f getAmbientLight() {
        return ambientLight;
    }

    /**
     * @param aAmbientLight the ambientLight to set
     */
    public static void setAmbientLight(Vector3f aAmbientLight) {
        ambientLight = aAmbientLight;
    }
    
    /**
     * @param directionalLight
     */
    public static void setDirectionalLight(DirectionalLight directionalLight) {
        PhongShader.directionalLight = directionalLight;
    }
    
    /**
     * sets uniform value for .color and .intensity of BaseLight
     * @param uniformName
     * @param baseLight 
     */
    public void setUniform(String uniformName, BaseLight baseLight) {
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniformf(uniformName + ".intensity", baseLight.getIntensity());
    }
    
    /**
     * Sets uniform value for .base and .direction of DirectionalLight
     * @param uniformName
     * @param directionalLight 
     */
    public void setUniform(String uniformName, DirectionalLight directionalLight) {
        setUniform(uniformName + ".base", directionalLight.getBase());
        setUniform(uniformName + ".direction", directionalLight.getDirection());
    }
    
}
