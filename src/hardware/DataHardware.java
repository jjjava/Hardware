package hardware;

/**
 *
 * @author hudson.sales
 */
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;

public class DataHardware {

    public static void main(String[] args) {

        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            System.out.println("Current host name : " + ip.getHostName());
            System.out.println("Current IP address : " + ip.getHostAddress());

            System.out.println("Full address : " + ip.getCanonicalHostName());
            String nameOS = System.getProperty("os.name");
            System.out.println("Operating system Name=>" + nameOS);
            String osType = System.getProperty("os.arch");
            System.out.println("Operating system type =>" + osType);
            String osVersion = System.getProperty("os.version");
            System.out.println("Operating system version =>" + osVersion);

            com.sun.management.OperatingSystemMXBean mxbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            Long l = mxbean.getTotalPhysicalMemorySize();
            String size = Long.toString(l);
            double d = Double.parseDouble(size);
            DecimalFormat decimal = new DecimalFormat("0.00");
            size = decimal.format(((d / 1024) / 1024) / 1024) + " GB";
            System.out.println("Total memória RAM: " + size);

            System.out.println(System.getenv("PROCESSOR_IDENTIFIER"));
            System.out.println(System.getenv("PROCESSOR_ARCHITECTURE"));
            System.out.println(System.getenv("PROCESSOR_ARCHITEW6432"));
            System.out.println(System.getenv("NUMBER_OF_PROCESSORS"));
            /* Total number of processors or cores available to the JVM */
            System.out.println("Available processors (cores): " + Runtime.getRuntime().availableProcessors());

            /* Total amount of free memory available to the JVM */
            System.out.println("Free memory (bytes): " + Runtime.getRuntime().freeMemory());
            System.out.println("Free memory (megas): " + ((Runtime.getRuntime().freeMemory() / 1024) / 1024));

            /* This will return Long.MAX_VALUE if there is no preset limit */
            long maxMemory = Runtime.getRuntime().maxMemory();
            /* Maximum amount of memory the JVM will attempt to use */
            System.out.println("Maximum memory (bytes): " + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

            /* Total memory currently in use by the JVM */
            System.out.println("Total memory (bytes): " + Runtime.getRuntime().totalMemory());
            System.out.println("Total memory (megas): " + ((Runtime.getRuntime().totalMemory() / 1024) / 1024));

            DecimalFormat df = new DecimalFormat("0.0");
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();

            long accelMemory = gd.getAvailableAcceleratedMemory();  // in bytes

            System.out.println("Initial Acc. Mem.: " + accelMemory);

            System.out.println("Initial Acc. Mem.: "
                    + df.format(((double) accelMemory) / (1024 * 1024)) + " MB");

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            System.out.println("Nome da interfce de rede: " + network.getName());
            byte[] mac = network.getHardwareAddress();
            System.out.print("Current MAC address : ");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            System.out.println(sb.toString());

            File[] roots = File.listRoots();
            for (File root : roots) {
                System.out.println("File system root: " + root.getAbsolutePath());
                System.out.println("Total space (bytes): " + root.getTotalSpace());
                System.out.println("Free space (bytes): " + root.getFreeSpace());
                System.out.println("Usable space (bytes): " + root.getUsableSpace());
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
