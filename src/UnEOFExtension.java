import org.nlogo.api.*;

public class UnEOFExtension extends DefaultClassManager {
    public void load(PrimitiveManager primitiveManager) {
        primitiveManager.addPrimitive
          ("file-at-end-now?", new UnEOF());
    }
}
