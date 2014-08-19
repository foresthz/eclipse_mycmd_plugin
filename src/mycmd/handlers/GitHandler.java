package mycmd.handlers;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.core.internal.resources.WorkspaceRoot;
import org.eclipse.core.internal.resources.projectvariables.ProjectLocationVariableResolver;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.variables.IStringVariableManager;
import org.eclipse.core.variables.VariablesPlugin;


/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class GitHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public GitHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
//		ProjectLocationVariableResolver resolver = new ProjectLocationVariableResolver();
		ResourcesPlugin.getWorkspace().getPathVariableManager().getPathVariableNames();
		IStringVariableManager strManager = VariablesPlugin.getDefault().getStringVariableManager();
		String workspace_loc="loc";
		try {
			workspace_loc = strManager.performStringSubstitution("${project_loc}");
			
			if (workspace_loc == null || workspace_loc=="") {
				MessageDialog.openInformation(
						window.getShell(),
						"Mycmd",
						"Popup Message: " + "please select a project");
			}

			
			Runtime runtime = Runtime.getRuntime();
			String cmdstr = "cmd /k " + workspace_loc.substring(0, 2) + " &cd " + workspace_loc + " & start";
			System.out.println(cmdstr);
			Process process = runtime.exec(cmdstr);
		} catch (CoreException e) {
			e.printStackTrace();
		}
//		resolver.getValue("ss", window);
		catch (IOException e) {
			e.printStackTrace();
		}
		

		return null;
	}
}
