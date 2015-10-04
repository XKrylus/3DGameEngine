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
public class Transform {
    
    private static Camera camera;
    
    //How close an object has to be to us, before it clips
    private static float zNear;
    
    //How far an object has to be from us, before it clips
    private static float zFar;
    
    //Screen dimensions
    private static float width;
    private static float height;
    
    //Filed Of View
    private static float fov;

    /**
     * @return the camera
     */
    public static Camera getCamera() {
        return camera;
    }

    /**
     * @param aCamera the camera to set
     */
    public static void setCamera(Camera aCamera) {
        camera = aCamera;
    }
    
    //Operations over object
    private Vector3f translation;
    private Vector3f rotation;
    private Vector3f scale;
    
    public Transform() {
        translation = new Vector3f(0, 0, 0);
        rotation = new Vector3f(0, 0, 0);
        scale = new Vector3f(1, 1, 1);
    }
    
    /**
     * Does the transformation over an Vector, which consists of scaling, 
     * rotation and translation.
     * @return transformed Matrix
     */
    public Matrix4f getTransformation() {
        
        Matrix4f translationMatrix = new Matrix4f().initTranslation(getTranslation().getX(), getTranslation().getY(), getTranslation().getZ());
        Matrix4f rotationMatrix = new Matrix4f().initRotation(getRotation().getX(), getRotation().getY(), getRotation().getZ());
        Matrix4f scaleMatrix = new Matrix4f().initScale(getScale().getX(), getScale().getY(), getScale().getZ());
        
        /* Order of multiplications is important! First we scale the Vector, 
           then we rotate it (we are keeping the original center) and only then
           can we perform translation.
        */
        return translationMatrix.mul(rotationMatrix.mul(scaleMatrix));
    }
    
    public Matrix4f getProjectedTransformation() {
        
        Matrix4f projectionMatrix = new Matrix4f().initProjection(fov, width, height, zNear, zFar);
        Matrix4f transformationMatrix  = getTransformation();
        
        // Camera implementation - what actually moves is the world, not the
        // "camera"
        Matrix4f cameraRotation = new Matrix4f().initCamera(camera.getForward(), camera.getUp());
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(-camera.getPos().getX(), -camera.getPos().getY(), -camera.getPos().getZ());
        
        return projectionMatrix.mul(cameraRotation.mul(cameraTranslation.mul(transformationMatrix)));
    }

    /**
     * @return the translation
     */
    public Vector3f getTranslation() {
        return translation;
    }
    
    /**
     * 
     * @param fov
     * @param width
     * @param height 
     * @param zNear 
     * @param zFar 
     */
    public static void setProjection(float fov, float width, float height, float zNear, float zFar) {
        
        Transform.fov = fov;
        Transform.width = width;
        Transform.height = height;
        Transform.zNear = zNear;
        Transform.zFar = zFar;
    }

    /**
     * @param translation the translation to set
     */
    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }
    
    /**
     * @param translation the translation to set
     */
    public void setTranslation(float x, float y, float z) {
        this.setTranslation(new Vector3f(x, y, z));
    }

    /**
     * @return the rotation
     */
    public Vector3f getRotation() {
        return rotation;
    }

    /**
     * @param rotation the rotation to set
     */
    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }
    
    /**
     * @param rotation the rotation to set
     */
    public void setRotation(float x, float y, float z) {
        this.setRotation(new Vector3f(x, y, z));
    }

    /**
     * @return the scale
     */
    public Vector3f getScale() {
        return scale;
    }

    /**
     * @param scale the scale to set
     */
    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
    
    /**
     * @param scale the scale to set
     */
    public void setScale(float x, float y, float z) {
        this.scale = new Vector3f(x, y, z);
    }
    
}
