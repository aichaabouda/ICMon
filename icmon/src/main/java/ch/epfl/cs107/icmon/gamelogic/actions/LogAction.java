/*
 * Auteur: Aicha Abouda
 * Date: 3 12
 */

package ch.epfl.cs107.icmon.gamelogic.actions;

public class LogAction implements Action {
    private String actionMess;

    // Constructeur de la classe
    public LogAction(String message){
        this.actionMess = message;
    }

    // Méthode pour effectuer l'action d'imprimer un message dans la console
    @Override
    public void perform() {
        System.out.println(actionMess);
    }
}
