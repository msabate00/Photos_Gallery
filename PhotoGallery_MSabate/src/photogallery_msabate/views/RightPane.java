/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package photogallery_msabate.views;

import java.io.File;
import javafx.scene.layout.VBox;
import photogallery_msabate.models.Img;

/**
 *
 * @author AitramKG
 */
public class RightPane extends VBox{
    
    public RightPane(){}
    
    public RightPane(Img i){
        
        if(getChildren().isEmpty()){
            getChildren().add(i);
        }else{
            getChildren().remove(1);
            getChildren().add(i);
        }
    }
    
    
    
}
