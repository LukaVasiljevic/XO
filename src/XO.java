enum STATUS_IGRE
{
    U_TOKU,
    POBEDIO_X,
    POBEDIO_O,
    NERESENO
}


public class XO
{
    /*
         x ->  1
         o -> -1
    */
    private final int[][] polja = new int[3][3];
    private int naPotezu;
    private STATUS_IGRE statusIgre;
    private int brojOdigranihPoteza;
    private int pobedaX = 0, pobedaO = 0;

    public XO()
    {
        inicijalizuj();
    }

    public void inicijalizuj()
    {
        naPotezu = 1;
        statusIgre = STATUS_IGRE.U_TOKU;
        brojOdigranihPoteza = 0;
        for (int i = 0; i < polja.length; i++) {
            for (int j = 0; j < polja.length; j++) {
                polja[i][j] = 0;
            }
        }
    }

    public void resetuj()
    {
        pobedaX = 0;
        pobedaO = 0;
        inicijalizuj();
    }

    public STATUS_IGRE odigrajPotez(int i, int j)
    {
        STATUS_IGRE status = STATUS_IGRE.U_TOKU;
        if( (i < 3 && i > -1) && (j < 3 && j > -1))
        {
            if(polja[i][j] == 0)
            {
                brojOdigranihPoteza++;
                polja[i][j] = naPotezu;
                naPotezu *= -1;

                status = provera();
            }
            statusIgre = status;
        }
        return status;
    }

    private STATUS_IGRE provera()
    {
        STATUS_IGRE status = STATUS_IGRE.U_TOKU;
        int i, j, ind = 0, indRed, indKol, indDia = 0, indSDia = 0;

        //PROVERA SUME
        for(i = 0; i < 3; i++)
        {
            indKol = 0;
            indRed = 0;
            for(j = 0; j < 3; j++)
            {
                indRed += polja[i][j];
                indKol += polja[j][i];
            }

            indDia += polja[i][i];
            indSDia += polja[i][3 - 1 - i];

            if(indRed == 3 || indKol == 3)
                ind = 1;
            else if(indRed == -3 || indKol == -3)
                ind = -1;

        }

        if(indDia == 3 || indSDia == 3)
            ind = 1;
        else if(indDia == -3 || indSDia == -3)
            ind = -1;

        if(brojOdigranihPoteza == 9)
            status = STATUS_IGRE.NERESENO;
        else if( ind == 1 )
        {
            status = STATUS_IGRE.POBEDIO_X;
            pobedaX++;
        }
        else if( ind == -1 )
        {
            status = STATUS_IGRE.POBEDIO_O;
            pobedaO++;
        }
        return status;
    }

    public int getPobedaX() {
        return pobedaX;
    }

    public int getPobedaO() {
        return pobedaO;
    }

    public int getNaPotezu() {
        return naPotezu;
    }

    @Override
    public String toString() {

        String polja = "";

        if(statusIgre == STATUS_IGRE.NERESENO)
            polja += "Igra je neresena!\n";
//        else
//            polja += "Na potezu je: " + koJeNaPotezu() + "\n";

        for (int i = 0; i < this.polja.length; i++) {
            polja += "\n";
            for (int j = 0; j < this.polja.length; j++) {
                polja += dajOznakuPolja(i, j);
            }
        }

        polja += "\n------------------------";

        return polja;
    }

    private String dajOznakuPolja(int i, int j) {

        if(polja[i][j] == 1)
            return " X ";
        else if(polja[i][j] == -1)
            return " O ";
        else return " - ";
    }

    private String koJeNaPotezu() {
        if(naPotezu == 1)
            return "X";
        else //if (naPotezu == -1)
            return "O";

    }
    public boolean validanPotez(int i, int j)
    {
        return polja[i][j] == 0;
    }
}
