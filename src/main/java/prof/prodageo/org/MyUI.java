
package prof.prodageo.org;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.HorizontalLayout;
import java.util.Arrays;
import java.util.List;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.VerticalLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* import for explicit declaration of callback */
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button.ClickEvent;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;


import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import java.util.* ;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("prof.prodageo.org.MyAppWidgetset")
public class MyUI extends UI {

        private static final Logger log = LoggerFactory.getLogger(MyUIServlet.class);

    /* explicit declaration as attributes of graphical components for GenMyModel */
        final VerticalLayout layout = new VerticalLayout();
        final HorizontalLayout hol = new HorizontalLayout();
        final HorizontalLayout hol2 =new HorizontalLayout();
        Button ajouter = new Button("ajouter") ;
        Button retirer = new Button("retirer") ;
        ListSelect playlist = new ListSelect("Playlist") ;
        ListSelect suggestion = new ListSelect("Suggestions") ;
        Controleur control ;
        final TextField rechercheField = new TextField();
        Button rechercher = new Button("rechercher") ;
        


    /* explicit callback */
    /* https://vaadin.com/docs/-/part/framework/application/application-events.html */
    public class ClickMeClass implements Button.ClickListener
    {
        public void buttonClick(ClickEvent event) 
        {
            String s=(String)rechercheField.getValue();

            control.entrerNomTag(s);
            if (!control.getList().isEmpty()){
                suggestion.addItems(control.getList());
            }
            

      
        }
    }
    public class ClickMeClass2 implements Button.ClickListener
    {
        public void buttonClick(ClickEvent event) 
        {
            control.chooseMusic((String)suggestion.getValue()) ;
            playlist.addItem(suggestion.getValue());
            suggestion.removeItem(suggestion.getValue());


        
        }
    }
        public class ClickMeClass3 implements Button.ClickListener
    {
        public void buttonClick(ClickEvent event) 
        {
            control.retirerMusique((String)playlist.getValue()) ;
            playlist.removeItem(playlist.getValue());


        
        }
    }
   

    
    public class gestionBarre implements Property.ValueChangeListener {


        public void valueChange(Property.ValueChangeEvent event) 
        {
            String s = (String)event.getProperty().getValue();
            control.entrerNomTag(s);
            if (!control.getList().isEmpty()){
                suggestion.addItems(control.getList());
            }



        }

    }

    public class gestionSauvergarde implements Property.ValueChangeListener {

         public void valueChange(Property.ValueChangeEvent event) 
        {
            Collection<String>  s = (Collection<String>)event.getProperty().getValue();
           
           // control.savePlaylist(s);

           

        }




    }    


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Controleur control =new Controleur();


        ClickMeClass callback = new ClickMeClass() ;
        ClickMeClass2 callback2 = new ClickMeClass2() ;
        ClickMeClass3 callback3 =new ClickMeClass3() ;


        this.control = control ;
        ArrayList<String> al = control.getPlaylist();

        gestionBarre gb = new gestionBarre() ;
        rechercheField.addListener(gb);
        rechercher.addClickListener( callback ) ;
        ajouter.addClickListener(callback2);
        retirer.addClickListener(callback3);





        playlist.addItems(al);
        playlist.setWidth("400px");
        suggestion.setWidth("400px");
         layout.addComponents(rechercher,rechercheField) ;
         hol.addComponents(ajouter,retirer);
         hol2.addComponents(suggestion ,playlist);
         layout.addComponents(hol2,hol);


         layout.setMargin(true);
         layout.setSpacing(true);

        
         setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
 



