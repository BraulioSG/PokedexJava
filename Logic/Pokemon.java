package Logic;

public class Pokemon extends PokemonAbstracta{
    

    public Pokemon() {
        this.name = "undefined";
        this.type = "undefined";
        this.num = "undefined";
        this.img = "./assets/missing-file.png";
        this.url = "https://www.pokemon.com/el/pokedex/";
        this.rating = -1;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setNum(String num){
        this.num = num;
    }

    public void setImg(String img){
        this.img = img;
    }

    public void setURL(String url){
        this.url = url;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public String getName(){
        return this.name;
    }

    public String getType(){
        return this.type;
    }

    public String getNum(){
        return this.num;
    }

    public String getImg(){
        return this.img;
    }

    public String getURL(){
        return this.url;
    }

    public int getRating(){
        return this.rating;
    }
    
    @Override
    public String toString(){
        String resultado = "\nNombre: " + this.name + "\nTipo: " + this.type;
        resultado += "\nNúmero: " + this.num + "\nRating: " + this.rating;
        resultado += "\nImagen: " + this.img + "\nURL: " + this.url;
        return resultado;
    }
}
