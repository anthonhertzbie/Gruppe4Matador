package MockUp;


import controller.Notifier;

public class Controller {
    private final Model model;
    private final Notifier notifier;




    public Controller(Notifier notifier){
        this.notifier = notifier;
        this.model = new Model();
    }

    public void test(){


        notifier.startGame(model);
    }

}
