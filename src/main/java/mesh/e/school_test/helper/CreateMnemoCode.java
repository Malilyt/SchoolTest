package mesh.e.school_test.helper;


public class CreateMnemoCode {

    int number;

    public String mnemoCode(int year){

        number = number + 1; // '=+' - вот так не нада!

        String mnemoCode = "SC-" + year + "T" +number;

        return mnemoCode;

    }

}
