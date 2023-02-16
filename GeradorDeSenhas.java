
import java.awt.*;
import java.awt.datatransfer.*;
import java.security.SecureRandom;
import javax.swing.*;

public class GeradorDeSenhas {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Olá, seja bem-vindo(a) ao Gerador de Senhas!", "Gerador de Senhas", JOptionPane.INFORMATION_MESSAGE);
        
        do {
            String tamanho = JOptionPane.showInputDialog(null, "Qual o tamanho da senha a ser gerada?", "Gerador de Senhas", JOptionPane.QUESTION_MESSAGE);
            int tamanhoSenha = Integer.parseInt(tamanho);

            Object[] opcoes = { "Sim", "Não" };
            int alfaNumerico = JOptionPane.showOptionDialog(null, "Deseja que a senha tenha caracteres alfanuméricos? (#, @, !)", "Gerador de Senhas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

            boolean useCaracteresEspeciais = (alfaNumerico != 0);

            String senhaGerada = gerarSenha(tamanhoSenha, useCaracteresEspeciais);

            JTextArea textArea = new JTextArea(senhaGerada);
            JScrollPane scrollPane = new JScrollPane(textArea);

            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(350, 150));
            JOptionPane.showMessageDialog(null, scrollPane, "Senha Gerada", JOptionPane.INFORMATION_MESSAGE);

            int opcaoCopiar = JOptionPane.showOptionDialog(null, "Deseja copiar a senha gerada para a área de transferência?", "Gerador de Senhas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
            if (opcaoCopiar == 0) {
                StringSelection stringSelection = new StringSelection(senhaGerada);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                JOptionPane.showMessageDialog(null, "Senha copiada para a área de transferência.", "Gerador de Senhas", JOptionPane.INFORMATION_MESSAGE);
            }

        } while (JOptionPane.showConfirmDialog(null, "Deseja gerar outra senha?", "Gerador de Senhas", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

        JOptionPane.showMessageDialog(null, "Obrigado por usar o Gerador de Senhas!", "Gerador de Senhas", JOptionPane.INFORMATION_MESSAGE);
    }

    public static String gerarSenha(int tamanhoSenha, boolean useCaracteresEspeciais) {
        final String senhaSemEspeciais = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final String senhaComEspeciais = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789{{!@#$%&*()/-_=+";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        String caracteres = useCaracteresEspeciais ? senhaComEspeciais : senhaSemEspeciais;
        int tamanhoCaracteres = caracteres.length();

        for (int i = 0; i < tamanhoSenha; i++) {
            int indice = random.nextInt(tamanhoCaracteres);
            sb.append(caracteres.charAt(indice));
        }

        return sb.toString();
    }
}
