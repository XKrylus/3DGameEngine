/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;

import java.util.Arrays;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author Honza
 */
public class ResourceLoader {
    
    public static Texture loadTexture(String fileName) {
        
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length - 1];
        
        try {
            // Gets texture in SlickUtil format -> .getTextureID return id
            int id = TextureLoader.getTexture(ext, new FileInputStream(new File("./res/textures/" + fileName))).getTextureID();
            
            return new Texture(id);
            
        }catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        return null;
    }
    
    /**
     * Loads shader from shader file, line by line
     * @param fileName name of the file containing shader
     * @return content of shader file
     */
    public static String loadShader(String fileName) {
        
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;
        
        try {
            shaderReader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
            String line;
            while((line = shaderReader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
            
            shaderReader.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        
        return shaderSource.toString();
    }
    
    
    /**
     * Loads mesh from .obj file.
     * @param fileName name of the file
     * @return mesh containing data
     */
    public static Mesh loadMesh(String fileName) {
        
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length - 1];
        
        Mesh res = new Mesh();
        
        if(!ext.equals("obj")) {
            System.err.println("Error: Mesh data file format not supported: " + ext);
        }
        
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Integer> indices = new ArrayList<Integer>();
        
        BufferedReader meshReader = null;
        
        try {
            meshReader = new BufferedReader(new FileReader("./res/models/" + fileName));
            String line;
            while((line = meshReader.readLine()) != null) {
                String[] tokens = line.split(" ");
                tokens = Util.removeEmptyStrings(tokens);
                
                if(tokens.length == 0 || tokens[0].equals("#"))
                    continue;
                else if(tokens[0].equals("v")) {
                    vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]),
                                                         Float.valueOf(tokens[2]),
                                                         Float.valueOf(tokens[3]))));
                }
                else if(tokens[0].equals("f")) {
                    
                    // OBJ STARTS FROM 1, WHILE I START FROM 0!! (reason for
                    // substraction)
                    indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                    
                    if(tokens.length > 4) {
                        indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
                    }
                }
            }
            
            
            meshReader.close();
            Vertex[] vertexData = new Vertex[vertices.size()];
            vertices.toArray(vertexData);
            
            Integer[] indexData = new Integer[indices.size()];
            indices.toArray(indexData);
            
            res.addVerticies(vertexData, Util.toIntArray(indexData));
            
            return res;
            
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        return null;
    }
    
}
