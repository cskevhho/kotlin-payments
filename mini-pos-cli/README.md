# Mini POS CLI

Super simple CLI point-of-sales project to get me acclimated to Kotlin! It allows users to view inventory, add and remove items from a cart, view the cart, and perform a checkout.

## Getting Started

To run this application, you will need to have Kotlin and Gradle installed on your system.

1.  Clone the repository
2.  Navigate to the project directory in your terminal.
3.  Build the project using Gradle:
    ```bash
    ./gradlew build
    ```
4.  Run the application using Gradle:
    ```bash
    ./gradlew run
    ```

## How to Use

Once the application is running, you will see a menu with the following options:

1.  **View Inventory:** Displays the list of available items with their IDs, names, prices, and current stock.
2.  **Add Item to Cart:** Allows you to add items to your cart by entering the item ID and the desired quantity (e.g., `2 3` to add 3 of item with ID 2).
3.  **Remove Item from Cart:** Allows you to remove items from your cart by entering the item ID and the quantity to remove (e.g., `1 1` to remove 1 of item with ID 1).
4.  **View Cart:** Shows the items currently in your shopping cart with their quantities and subtotals.
5.  **Checkout:** Processes the items in your cart, calculates the total, prints a receipt, and clears the cart.
6.  **Exit:** Closes the application.

Simply enter the number corresponding to the action you want to perform and follow the on-screen prompts.

## Notes

* The inventory data is currently initialized within the code.
* This is a basic implementation for demonstration purposes and familiarizing myself with idiomatic Kotlin (we love null safety in this household)
