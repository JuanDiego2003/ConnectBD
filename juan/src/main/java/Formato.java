import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formato {
    public static String[] listaString(String text, boolean porComas) {
        String[] textSeparado = null;
        text = text.replace("[", "");
        text = text.replace("]", "");
        if (porComas) {
            text = text.replace("'", "");
            textSeparado = text.split(",");
        } else {
            text = text.replace("', \"", "', '");
            text = text.replace("\", '", "', '");
            text = text.replace("\", \"", "', '");
            text = text.replace(".'", "', '");

            text = text.replaceFirst("'", "");
            textSeparado = text.split("', '");
        }
        for (int i = 0; i < textSeparado.length; i++) {
            textSeparado[i] = textSeparado[i].replace(", '", "");
            textSeparado[i] = textSeparado[i].replace("\"", "");
            textSeparado[i] = textSeparado[i].replaceFirst("'", "");
            if (textSeparado[i].endsWith("'")) {
                textSeparado[i] = textSeparado[i].substring(0, textSeparado[i].length() - 1);
                if (textSeparado[i].endsWith("'")) {
                    textSeparado[i] = textSeparado[i].substring(0, textSeparado[i].length() - 1);
                }
            }
        }
        return textSeparado;
    }

    public static int formatoNumero(String num) {
        int numero = 0;
        if (num.toLowerCase().contains("k")) {
            if (!num.toLowerCase().contains(".")) {
                num = num.toLowerCase().replace("k", "000");
            } else {
                num = num.replace(".", "");
                num = num.toLowerCase().replace("k", "00");
            }
        }
        numero = Integer.parseInt(num);
        return numero;
    }

    public static Date formatoFecha(String fecha) throws ParseException {
        String[] formato = fecha.split(" ");

        formato[1] = formato[1].replace(",", "");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if (formato[0].equalsIgnoreCase("jan")) {
            formato[0] = "1";
        } else if (formato[0].equalsIgnoreCase("feb")) {
            formato[0] = "2";
        } else if (formato[0].equalsIgnoreCase("mar")) {
            formato[0] = "3";
        } else if (formato[0].equalsIgnoreCase("apr")) {
            formato[0] = "4";
        } else if (formato[0].equalsIgnoreCase("may")) {
            formato[0] = "5";
        } else if (formato[0].equalsIgnoreCase("jun")) {
            formato[0] = "6";
        } else if (formato[0].equalsIgnoreCase("jul")) {
            formato[0] = "7";
        } else if (formato[0].equalsIgnoreCase("aug")) {
            formato[0] = "8";
        } else if (formato[0].equalsIgnoreCase("sep")) {
            formato[0] = "9";
        } else if (formato[0].equalsIgnoreCase("oct")) {
            formato[0] = "10";
        } else if (formato[0].equalsIgnoreCase("nov")) {
            formato[0] = "11";
        } else if (formato[0].equalsIgnoreCase("dec")) {
            formato[0] = "12";
        }
        Date fech = null;
        if (!fecha.equalsIgnoreCase("releases on TBD")) {
            fech = format.parse(formato[1] + "/" + formato[0] + "/" + formato[2]);
        } else {
            fech = format.parse("1/1/23");
        }
        return fech;
    }
}
