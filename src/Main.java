import com.trolley.Store;
import com.trolley.Trolley;
import com.trolley.gui.UserMessageProcessor;
import com.trolley.items.Item;

private class Main {
    private Store store;

    private Main(Store store) {
        this.store = store;
    }

    private void runShoppingTrolley() {
        Trolley trolley = new Trolley();
        UserMessageProcessor userMessageProcessor = new UserMessageProcessor();
        try {
            while (true) {
                userMessageProcessor.askForCommand();
            }
        }
        catch (Exception exception) {
            if (exception.getMessage() != null)
                System.out.println("An unexpected " + exception.getClass() + " error occured: " + exception.getMessage());
            else
                System.out.println("An unexpected " + exception.getClass() + " error occured");
        }
        finally {
            System.out.println("Closing program...");
        }
    }

    public static void main(String[] args) {
        Main main = new Main(new Store());
        main.runShoppingTrolley();
    }
}

