import org.nlogo.api.*;
import org.nlogo.workspace.*;
import org.nlogo.nvm.ExtensionContext;
import org.nlogo.nvm.FileManager;
import org.nlogo.nvm.Workspace;

public class UnEOF extends DefaultReporter {

	public Syntax getSyntax() {
		return Syntax.reporterSyntax(new int[] {/*no args*/}, Syntax.BooleanType());
	}

	public Object report(Argument args[], Context context)
	   throws ExtensionException {

		ExtensionContext extContext = (ExtensionContext) context;
		Workspace wkspc = extContext.workspace();
		DefaultFileManager filemgr = (DefaultFileManager) wkspc.fileManager();
		File currFile = filemgr.currentFile();

		try {
			filemgr.ensureMode(org.nlogo.api.FileModeJ.READ());
			// The next four lines are copied from 
			// NetLogo/src/main/org/nlogo/workspace/DefaultFileManager.java, which is
			// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo
			java.io.BufferedReader buffReader = currFile.reader();
			buffReader.mark(2);
			currFile.eof_$eq(buffReader.read() == -1);
			buffReader.reset();
			return currFile.eof();
		} catch (Throwable e) {
			throw new ExtensionException( e.getMessage() ) ;
		}
	}
}


