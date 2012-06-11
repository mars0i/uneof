uneof
=====

The UnEOF extension for NetLogo. 

# NetLogo uneof extension

This extension provides exactly one primitive, uneof:file-at-end-now? .  This can be viewed as a replacement for NetLogo's built-in file-at-end?

When NetLogo reads a file using primitives such as file-read, it will eventually reach the end of the file, unless data is continually added to the file faster than NetLogo reads it.  Once NetLogo encounters the end of file, it normally assumes that it this state is permanent.  That is, NetLogo assumes that new data will never be added to the file.  If you call end-of-file?, it will always return true from then on.  If new data is added after the location of the (former) end of file, the only way to get at it is to reopen the file and start reading from the beginning.

uneof:file-at-end-now? allows you to undo this end-of-file status.  It checks whether the point in the file that NetLogo is looking at really is the end of file.  If it once was the end of the file, but no longer is, file-at-end-now? will change the status.  This then allows NetLogo to read data that was added to the same file after the end of file was initially encountered.  

NetLogo 5.02 or later is required.  (Development releases beginning from around May 17, 2012 should work.)

## Development status

Seems to work consistently.  

(I have experienced errors in which NetLogo seemed to fail to read all of a NetLogo expression in a file, but I think that was because I had altered the file that NetLogo was reading in a way that would confuse most possible file-reading procedures.  I think these errors were not uneof's fault, and not NetLogo's fault.  Please let me know if you experience an error that seems to be due to uneof.)

## Usage

The provided primitives are:

 * `uneof:file-at-end-now?` (reporter)


## Building

The extension is written in Java (version 1.6).

If compilation succeeds, `uneof.jar` will be created.

## Credits

Written by Marshall Abrams, but several lines of code are copied from NetLogo/src/main/org/nlogo/workspace/DefaultFileManager.java, which is (C) Uri Wilensky. https://github.com/NetLogo/NetLogo .

Thanks to Seth Tisue for adding the FileManager.currentFile() method to the NetLogo 5.0.2 source code, and for several tips pointing me in the right direction.  

## Terms of Use

As noted above, several lines of code are copied from NetLogo/src/main/org/nlogo/workspace/DefaultFileManager.java, which is (C) Uri Wilensky. https://github.com/NetLogo/NetLogo .  These lines are marked in the source code.  All other code is by Marshall Abrams, and is in the public domain.
