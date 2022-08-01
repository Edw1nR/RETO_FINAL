package MODELO;

public class SUCURSAL {

    private int idSucursal;
    private String nombreSucursal;

    public SUCURSAL(){

        this.idSucursal = 0;
        this.nombreSucursal = "";
        

}

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }


    @Override
    public String toString() {
        return getNombreSucursal();
    }

    
}
