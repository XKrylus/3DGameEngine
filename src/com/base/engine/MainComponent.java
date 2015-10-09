/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Honza
 */
public class MainComponent {
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "3D engine";
    public static final double FRAME_CAP = 5000.0;
    
    private boolean isRunning;
    private Game game;
    
    public MainComponent() {
        
        //Print current OpenGL version avalible
        System.out.println(RenderUtil.getOpenGLVersion());
        
        RenderUtil.initGraphics();
        
        isRunning = false;
        game = new Game();
        
    }
    
    /**
     * Does initialization of various components before actual game loop. Sets
     * {@link #isRunning} to true. Calls {@link #run()} afterwards to initiate
     * game loop.
     */
    public void start() {
        
        if(isRunning) return;
        
        run();
    }
    
    
    /**
     * Takes care of stopping game loop, which is achieved by setting {@link 
     * #isRunning} to false.
     */
    public void stop() {
        
        if(!isRunning) return;
        isRunning = false;
    }
    
    /**
     * Game engine loop, runs continuously to allow rendering and input calls.
     * Rendering is achieved by calling {@link #render()} method.
     */
    private void run() {
        
        isRunning = true;
        
        // Calculates frame time given by frame cap variable
        final double frameTime = 1.0 / FRAME_CAP;
        
        
        // Last time of rendering
        long lastTime = Time.getTime();
        
        // Time remaining to complete current render
        double unprocessedTime = 0;
        
        // Number of frames
        int frames = 0;
        
        // Counts number of frames in second
        int frameCounter = 0;
        
        // Game loop. Continues as long Close request is not called
        while(isRunning) {
            
            boolean render = false;
            
            // Calculates time since last render (ie. how long the rendering took
            // )
            long startTime = Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;
            
            //How much time is needed for rendering
            unprocessedTime += passedTime / (double)Time.SECOND;
            frameCounter += passedTime;
            
            // If we did not manage to render in time, give option for next
            // update
            while(unprocessedTime > frameTime) {
                
                render = true;
                
                unprocessedTime -= frameTime;
                
                if(Window.isCloseRequested()) stop();
                
                //Always set delta (time diff)!
                Time.setDelta(frameTime);
                
                
                game.input();
                Input.update();
                
                //lockUpdate disables updating, controlled by pressing space
                if(InputControl.lockUpdate)game.update();
                
                if(frameCounter >= Time.SECOND) {
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }
            
            // Allow next rendering if we managed to draw everything. Otherwise
            // wait one milisecond.
            if(render) {
                render();
                frames++;
            }
            else try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        // After game loop ends, do the clean up of resources
        cleanUp();
    }
    
    /**
     * Takes care of rendering. Calls both game logic render and Window render.
     */
    private void render() {
        
        RenderUtil.clearScreen();
        
        game.render();
        Window.render();
    }
    
    /**
     * Takes care of cleaning up after window is closed.
     */
    private void cleanUp() {
        
        Window.dispose();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Window.createWindow(WIDTH, HEIGHT, TITLE);

        MainComponent game = new MainComponent();
        game.start();
    }
    
}
