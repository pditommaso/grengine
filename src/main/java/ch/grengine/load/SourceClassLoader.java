/*
   Copyright 2014-now by Alain Stalder. Made in Switzerland.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package ch.grengine.load;

import ch.grengine.except.CompileException;
import ch.grengine.except.LoadException;
import ch.grengine.source.Source;


/**
 * Abstract class loader that can load the main and other classes of a source.
 * 
 * @since 1.0
 * 
 * @author Alain Stalder
 * @author Made in Switzerland.
 */
public abstract class SourceClassLoader extends ClassLoader implements Cloneable {
    
    /**
     * constructor from parent class loader.
     * 
     * @since 1.0
     */
    public SourceClassLoader(ClassLoader parent) {
        super(parent);
    }

    /**
     * loads the main class of the given source.
     * <p>
     * First searches for the source, then loads the main class that resulted
     * from compiling the source.
     * 
     * @throws CompileException if compilation was necessary to load the class and failed
     * @throws LoadException if loading failed, including if the class was not found
     * 
     * @since 1.0
     */
    public abstract Class<?> loadMainClass(Source source) throws CompileException, LoadException;
    
    /**
     * loads a class with the given name and from the given source.
     * <p>
     * First searches for the source, only then for the class with given name
     * as part of the classes that resulted from compiling the source.
     * 
     * @throws CompileException if compilation was necessary to load the class and failed
     * @throws LoadException if loading failed, including if the class was not found
     * 
     * @since 1.0
     */
    public abstract Class<?> loadClass(Source source, String name) throws CompileException, LoadException;
    
    /**
     * tries to find the bytecode class loader that can load classes that were created
     * by compiling the given source.
     * 
     * @return bytecode class loader if found, null otherwise
     * 
     * @since 1.0
     */
    public abstract BytecodeClassLoader findBytecodeClassLoaderBySource(Source source);
    
    /**
     * gets the load mode.
     * 
     * @since 1.0
     */
    public abstract LoadMode getLoadMode();
    
    /**
     * creates a clone with identical behavior, typically sharing the same bytecode.
     * 
     * @since 1.0
     */
    @Override
    public abstract SourceClassLoader clone();

}
