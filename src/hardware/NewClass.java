package hardware;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author hudson.sales
 */
public class NewClass {

    public Image getImageFromClipboard() {
        Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            try {
                return (Image) transferable.getTransferData(DataFlavor.imageFlavor);
            } catch (UnsupportedFlavorException e) {
                System.err.println(e);
            } catch (IOException e) {
                System.err.println(e);
            }
        } else {
            System.err.println("getImageFromClipboard:not an image!");
        }
        return null;
    }

    public static void main(String args[]) {
        NewClass newclass = new NewClass();
        Image image  = newclass.getImageFromClipboard();
        System.out.println(image.getWidth(null));
    }
}
