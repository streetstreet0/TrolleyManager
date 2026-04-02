import com.trolley.Store;
import com.trolley.Trolley;
import com.trolley.gui.UserMessageProcessor;

public class ShoppingTrolleyManager {
    private Store store;

    private ShoppingTrolleyManager(Store store) {
        this.store = store;
    }

    private void runShoppingTrolley() {
        Trolley trolley = new Trolley();

        UserMessageProcessor userMessageProcessor = new UserMessageProcessor(store, trolley);
        Thread userMessageProcessorThread = new Thread(userMessageProcessor);
        try {
            userMessageProcessor.run();
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
        ShoppingTrolleyManager shoppingTrolleyManager = new ShoppingTrolleyManager(new Store());
        shoppingTrolleyManager.runShoppingTrolley();
    }
}

