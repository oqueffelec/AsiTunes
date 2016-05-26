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
import com.vaadin.ui.Panel ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* import for explicit declaration of callback */
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button.ClickEvent;
import java.util.ArrayList;


public class Controleur{
  private Playlist maPlaylist;
  private Playlist playlistRap;
  private Playlist playlistPop;
  private Playlist playlistRock;
  private Musique bjean;
  private Musique rstones;
  private Musique lyourself;
  private Musique maMusique1;
  private Musique maMusique2;
  private Musique maMusique3;
  private ArrayList array;
  private ArrayList array1;
  private ArrayList array3;
  private ArrayList array4;
  private ArrayList suggestions;

  public Controleur(){
    array= new ArrayList();
    array1= new ArrayList();
    array3= new ArrayList();
    array4= new ArrayList();
    suggestions=new ArrayList();
    bjean= new Musique("Billie Jean");
    rstones=new Musique("Rolling Stones");
    lyourself=new Musique("Lose Yourself");
    maMusique1= new Musique("Happy");
    maMusique2=new Musique("piano");
    maMusique3=new Musique("violon");
    playlistRap= new Playlist(array);
    playlistRap.ajouterMusique(lyourself);
    playlistPop= new Playlist(array1);
    playlistPop.ajouterMusique(bjean);
    playlistRock= new Playlist(array3);
    playlistRock.ajouterMusique(rstones);
    maPlaylist=new Playlist(array4);
    maPlaylist.ajouterMusique(maMusique1);
    maPlaylist.ajouterMusique(maMusique2);
    maPlaylist.ajouterMusique(maMusique3);

  }

  public void entrerNomTag(String tag ){
        this.suggestions=getMusics(tag);
  }

  public ArrayList<String> getPlaylist(){
    return this.maPlaylist.getMusiques();
  }

  public ArrayList<String> getList(){
    return this.suggestions;
  }
  public ArrayList<String> getMusics(String tag){
    if(tag.equals("rap")){
      return playlistRap.getMusiques();
    }
    else if(tag.equals("pop")){
      return playlistPop.getMusiques();
    }
    else if(tag.equals("rock")){
      return playlistRock.getMusiques();
    }
    else
     return null;
  }

public void chooseMusic(String nomMusique){
  String data = getMusic(nomMusique);
  Musique musique=creerMusique(nomMusique);
  this.maPlaylist.ajouterMusique(musique);
}
  public String getMusic(String nomMusique){
    return nomMusique;
  }


  public void retirerMusique(String  nomMusique){
    this.maPlaylist.retirerMusique(nomMusique);
  }

  public Musique creerMusique(String nomMusique){
    Musique musique= new Musique(nomMusique);
    return musique;
  }




  public class Musique{
    private String musique;
    public Musique(String musique){
      this.musique=musique;
    }

    public void entrerNomMusique(String nomMusique){
      verifierNomMusique(nomMusique);
    }

    public String getNom(){
      return this.musique;
    }

    public boolean verifierNomMusique(String nomMusique){
      if (nomMusique=="Billie Jean"||nomMusique=="Rolling Stones"||nomMusique=="Lose Yourself")
        return true;
      else
        return false;
    }


}

  public class Playlist{
    private ArrayList<Musique> musiques;;
    private String nomPlaylist;

    public Playlist(ArrayList<Musique> musiques){
      this.musiques= musiques;
    }
    public void ajouterMusique(Musique musique){
      musiques.add(musique);
    }

    public void retirerMusique(String nomMusique){
      Musique mus=null ;
      for(Musique m : this.musiques){
        if (m.getNom().equals(nomMusique)){
              mus=m;
        }
      }
      musiques.remove(mus);
    }


    public ArrayList<String> getMusiques(){
      ArrayList<String> array2 =new ArrayList();
        for(Musique m : this.musiques){
          array2.add(m.getNom());
        }
        return array2;
      }

    public String getNom(){
      return this.nomPlaylist;
    }

  }









}
