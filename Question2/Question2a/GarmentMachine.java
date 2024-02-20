package Question2a;

public class GarmentMachine {
    
    // Method to calculate the minimum moves required to balance the devices
    public int minimumMoves(int[] devices) {
        // Initialize total to keep track of the total number of garments
        int total = 0;
        // Calculate the total number of garments
        for (int item : devices) {
            total += item;
        }
    
        // Get the count of devices
        int count = devices.length;
        // If the total number of garments is not evenly divisible by the count of devices, return -1
        if (total % count != 0) {
            return -1;
        }
    
        // Calculate the target number of garments each device should have
        int target = total / count;
    
        // Initialize steps to keep track of the number of moves required
        int steps = 0;

        // Boolean flag to check if the garments are balanced
        boolean balanced = false;
        // Loop until the garments are balanced
        while (!balanced) {
            balanced = true;
    
            // Iterate over devices to check and balance if any device has more garments than the target
            for (int i = 0; i < count - 1; i++) {
                if (devices[i] > target) {
                    devices[i]--;
                    devices[i + 1]++;
                    steps++;
                    balanced = false;
                }
            }

            // Iterate over devices in reverse order to check and balance if any device has more garments than the target
            for (int i = count - 1; i > 0; i--) {
                if (devices[i] > target) {
                    devices[i]--;
                    devices[i - 1]++;
                    steps++;
                    balanced = false;
                }
            }
        }
    
        // Return the total number of steps required to balance the garments
        return steps;
    }
    
    public static void main(String[] args) {
        // Sample devices array
        int[] devices = {1, 0, 5};
        // Create an instance of GarmentMachine
        GarmentMachine machine = new GarmentMachine();
        // Call minimumMoves method and print the result
        System.out.println("Minimum moves required: " + machine.minimumMoves(devices));
    }
}
