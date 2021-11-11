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


public class ClientUI implements Runnable{
    public ClientUI()
    {
        //Client UI
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Update personal data ",  new UpdateClientUI()));
        options.add(new MenuItem("See your tests ",  new CheckTestsUI()));
        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nClient Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
*/

