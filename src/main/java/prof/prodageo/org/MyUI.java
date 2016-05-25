
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
        final TextField rechercheField = new TextField();
        Button rechercher = new Button("rechercher") ;
        private  com.vaadin.ui.TextArea editor;
      
        final HorizontalLayout hol = new HorizontalLayout();
        private TwinColSelect twc ;
        


    /* explicit callback */
    /* https://vaadin.com/docs/-/part/framework/application/application-events.html */
    public class ClickMeClass implements Button.ClickListener
    {
        public void buttonClick(ClickEvent event) 
        {
        
            layout.addComponent(new Label(rechercheField.getValue()));
        }
    }

   

    
    public class gestionBarre implements Property.ValueChangeListener {


        public void valueChange(Property.ValueChangeEvent event) 
        {
            String s = (String)event.getProperty().getValue();
            layout.addComponent(new Label(s));


        }

    }

        


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        
        // final VerticalLayout layout = new VerticalLayout();
        
        // final TextField name = new TextField();
        rechercheField.setCaption("entrez un tag ou une musique");

        /*
        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
            log.info("Button clicked with value : " + name.getValue());
        });
        */
        ClickMeClass callback = new ClickMeClass() ;
        
        layout.addComponents(rechercheField, rechercher);
         hol.setWidth("400px");
         hol.setWidth("400px");
         //List<String> list= Arrays.asList(new String[] {
           // "Berlin", "Brussels", "Helsinki", "Madrid", "Oslo", "Paris",
           // "Stockholm" });
         //musiqueTag = new ListSelectSingleExample(hol,"musiques recherchées",list);
         //maPlay = new ListSelectSingleExample(hol,"  ma playlist" ,list);

         TwinColSelect twc = new TwinColSelect("Select Targets");

        // Put some items in the select
        twc.addItems("Mercury", "Venus", "Earth", "Mars",
        "Jupiter", "Saturn", "Uranus", "Neptune");

        // Few items, so we can set rows to match item count
        twc.setRows(twc.size());

        // Preselect a few items by creating a set
        HashSet<String> hs = new  HashSet<String>( Arrays.asList("Mercury", "Venus", "Earth", "Mars",
        "Jupiter", "Saturn", "Uranus", "Neptune"));
        twc.setValue(hs);

        // Handle value changes
        twc.addValueChangeListener(event -> // Java 8
        layout.addComponent(new Label("maPlaylist: " + event.getProperty().getValue())));
        hol.addComponent(twc);
        

        

        gestionBarre gb = new gestionBarre() ;
        rechercheField.addListener(gb);
        rechercher.addClickListener( callback ) ;
        

         layout.addComponent(hol);
         layout.setMargin(true);
         layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
 



