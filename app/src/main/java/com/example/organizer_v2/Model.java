/** This holde the model  class with an id, name, tag, and image.
 * the values of the database top or bottom can be passed through the Model class to be created as an object
 *    @author Nesa Bertanico
 *    @version 1.0
 */
package com.example.organizer_v2;


public class Model {
    private int id;
    private String name, tags;
    private byte[] image;
//id, name, tag, image
    public Model(int i, String n, String t, byte[] img){ //id, name, tag, image)
        this.id = i;
        this.name = n;
        this.tags = t;
        this.image = img;
    }

    /**
     *getter for id
     * @return id
     */
    public int getId() {return id;}

    /**
     *getter for name
     * @return name
     */
    public String getName() {return name;}

    /**
     *getter for tags
     * @return tags
     */
    public String getTags() {return tags;}

    /**
     *getter for image
     * @return image
     **/
    public byte[] getImage() {return image;}
}
