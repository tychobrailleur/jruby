package org.jruby.ir.targets;

import org.jruby.ir.instructions.CallBase;
import org.jruby.ir.instructions.EQQInstr;

public interface InvocationCompiler {
    /**
     * Invoke a method on an object other than self.
     * <p>
     * Stack required: context, self, all arguments, optional block
     *
     * @param call the call to be invoked
     */
    void invokeOther(String file, int line, String scopeFieldName, CallBase call, int arity);

    /**
     * Invoke the array dereferencing method ([]) on an object other than self.
     * <p>
     * If this invokes against a Hash with a frozen string, it will follow an optimized path.
     * <p>
     * Stack required: context, self, target, arg0
     *
     * @param file
     * @param line
     */
    void invokeArrayDeref(String file, int line, String scopeFieldName, CallBase call);

    /**
     * Invoke the to_s method with AsString semantics (tainting, refinements, etc).
     * <p>
     * Stack required: context, self, target
     *
     * @param file
     * @param line
     */
    void invokeAsString(String file, int line, String scopeFieldName, CallBase call);

    /**
     * Invoke a fixnum-receiving method on an object other than self.
     * <p>
     * Stack required: context, self, receiver (fixnum will be handled separately)
     */
    void invokeOtherOneFixnum(String file, int line, CallBase call, long fixnum);

    /**
     * Invoke a float-receiving method on an object other than self.
     * <p>
     * Stack required: context, self, receiver (float will be handled separately)
     */
    void invokeOtherOneFloat(String file, int line, CallBase call, double flote);

    /**
     * Invoke a method on self.
     *
     * Stack required: context, caller, self, all arguments, optional block
     *
     * @param file the filename of the script making this call
     * @param line the line number where this call appears
     * @param call to be invoked on self
     * @param arity of the call.
     */
    void invokeSelf(String file, int line, String scopeFieldName, CallBase call, int arity);

    /**
     * Invoke a superclass method from an instance context.
     *
     * Stack required: context, caller, self, start class, arguments[, block]
     *
     * @param file the filename of the script making this call
     * @param line the line number where this call appears
     * @param name name of the method to invoke
     * @param arity arity of the arguments on the stack
     * @param hasClosure whether a block is passed
     * @param splatmap a map of arguments to be splatted back into arg list
     */
    void invokeInstanceSuper(String file, int line, String name, int arity, boolean hasClosure, boolean[] splatmap);

    /**
     * Invoke a superclass method from a class context.
     *
     * Stack required: context, caller, self, start class, arguments[, block]
     *
     * @param file the filename of the script making this call
     * @param line the line number where this call appears
     * @param name name of the method to invoke
     * @param arity arity of the arguments on the stack
     * @param hasClosure whether a block is passed
     * @param splatmap a map of arguments to be splatted back into arg list
     */
    void invokeClassSuper(String file, int line, String name, int arity, boolean hasClosure, boolean[] splatmap);

    /**
     * Invoke a superclass method from an unresolved context.
     *
     * Stack required: context, caller, self, arguments[, block]
     *
     * @param file the filename of the script making this call
     * @param line the line number where this call appears
     * @param name name of the method to invoke
     * @param arity arity of the arguments on the stack
     * @param hasClosure whether a block is passed
     * @param splatmap a map of arguments to be splatted back into arg list
     */
    void invokeUnresolvedSuper(String file, int line, String name, int arity, boolean hasClosure, boolean[] splatmap);

    /**
     * Invoke a superclass method from a zsuper in a block.
     *
     * Stack required: context, caller, self, arguments[, block]
     *
     * @param file the filename of the script making this call
     * @param line the line number where this call appears
     * @param name name of the method to invoke
     * @param arity arity of the arguments on the stack
     * @param hasClosure whether a block is passed
     * @param splatmap a map of arguments to be splatted back into arg list
     */
    void invokeZSuper(String file, int line, String name, int arity, boolean hasClosure, boolean[] splatmap);

    /**
     * Perform a === call appropriate for a case/when statement.
     *
     * Stack required: context, case value, when value
     */
    void invokeEQQ(EQQInstr call);
}
