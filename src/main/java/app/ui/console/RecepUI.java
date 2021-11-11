/*
package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

*/
/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 *//*


public class RecepUI implements Runnable{
    public RecepUI()
    {
        //empty
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Register a new client ",  new CreateClientUI()));
        options.add(new MenuItem("Register a new Test", new CreateTestUI()));
        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
*/

