package com.mark.gen;

import javassist.*;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by lulei on 2016/10/14.
 */
public class ProxyDemo {

    public static void main(String[] args) throws Exception, CannotCompileException, IOException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException {
//        ClassPool pool = ClassPool.getDefault();
//        CtClass cc = pool.get("com.mark.gen.Animal");
//        cc.setSuperclass(pool.get("com.mark.gen.Cat"));
//
//        byte[] b = cc.toBytecode();
//        cc.writeFile();
//
//        Class clazz = cc.toClass();
//        Cat cat = (Cat) clazz.newInstance();

//        ClassPool pool = ClassPool.getDefault();
//        CtClass cc = pool.makeClass("Point");
//        CtClass cc2 = pool.makeInterface("Animal");
//        cc.writeFile();;
//        cc.defrost();
//        cc.setSuperclass();

//        pool.insertClassPath(new ClassClassPath(ProxyDemo.class.getClass()));

//        String str = URLEncoder.encode("秋玉燕");
//        System.out.println(str);

//        ClassPool pool = ClassPool.getDefault();
//        pool.insertClassPath("/usr/local/javalib");
//
//        ClassPath cp = new URLClassPath("www.javassist.org", 80, "/java/", "org.javassist.");
//        pool.insertClassPath(cp);
//
//        CtClass cc = pool.makeClass("dog");
//        cc.writeFile();
//        cc.detach();

//        ClassPool cp = new ClassPath();
//        cp.appendSystemPath();

//        ClassPool parent = ClassPool.getDefault();
//        ClassPool child = new ClassPool(parent);
//        child.appendSystemPath();
//        child.childFirstLookup = true;

//        ClassPool pool = ClassPool.getDefault();
//        CtClass cc = pool.get("Point");
//        cc.setName("Pair");

//        ClassPool cp = ClassPool.getDefault();
//        CtClass cc = cp.get("com.mark.gen.Hello");
//        CtMethod m = cc.getDeclaredMethod("say");
//        m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
//        Class c = cc.toClass();
//        Hello h = (Hello) c.newInstance();
//        h.say();

//        Hello orig = new Hello();
//        ClassPool cp = ClassPool.getDefault();
//        CtClass cc = cp.get("com.mark.gen.Hello");
//        cc.toClass(Hello.class.getClassLoader(),null);

//        ClassPool pool = ClassPool.getDefault();
//        Loader cl = new Loader(pool);
//
//        CtClass ct = pool.get("com.mark.gen.Hello");
//        ct.setSuperclass(pool.get("parent"));
//
//        Class c = cl.loadClass("test");
//        Object rect = c.newInstance();

//        ClassPool cp = ClassPool.getDefault();
//        CtClass cc =  cp.get("com.mark.gen.Hello");
//        CtMethod m = cc.getDeclaredMethod("add");
//        m.insertAfter("{ $1++; $2++; }");
//        m.insertAfter("{ System.out.println($1); System.out.println($2); }");
//        m.insertAfter("{System.out.println($args);}");
//        cc.writeFile();;
//        Class clazz = cc.toClass();
//        Hello hello = (Hello) clazz.newInstance();
//        hello.add(1,2);

//        CtClass cc = ClassPool.getDefault().get("com.mark.gen.Hello");
//        CtMethod m = CtMethod.make("public int show(){System.out.println('hello');}",cc);
//        cc.addMethod(m);
//        Class clazz = cc.toClass();
//        Hello hello = (Hello) clazz.newInstance();
//
//        BufferedInputStream fin = new BufferedInputStream(new FileInputStream("Hello.class"));
//        ClassFile cf = new ClassFile(new DataInputStream(fin));


//        ClassFile cf = new ClassFile(false,"test.Foo",null);
//        cf.setInterfaces(new String[]{"java.lang.Cloneable"});
//
//        FieldInfo f = new FieldInfo(cf.getConstPool(),"width","I");
//        f.setAccessFlags(AccessFlag.PUBLIC);
//        cf.addField(f);
//
//        cf.write(new DataOutputStream(new FileOutputStream("Foo.class")));

//        ClassPool cp = ClassPool.getDefault();
//        CtClass ctClass = cp.makeClass("com.Hello");
//        StringBuffer body = null;
//
//        CtField ctField = new CtField(cp.get("java.lang.String"), "name", ctClass);
//        ctField.setModifiers(Modifier.PRIVATE);
//        ctClass.addField(ctField, CtField.Initializer.constant("default"));
//
//        ctClass.addMethod(CtNewMethod.setter("setName", ctField));
//        ctClass.addMethod(CtNewMethod.getter("getName", ctField));
//
//        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
//        body = new StringBuffer();
//        body.append("{\n name=\"mark\";\n}");
//        ctConstructor.setBody(body.toString());
//        ctClass.addConstructor(ctConstructor);
//
//
//        CtMethod ctMethod = new CtMethod(CtClass.voidType, "execute", new CtClass[]{}, ctClass);
//        ctMethod.setModifiers(Modifier.PUBLIC);
//        body = new StringBuffer();
//        body.append("{\n System.out.println(name);");
//        body.append("\n System.out.println(\"execute ok\");");
//        body.append("\n return;");
//        body.append("\n}");
//
//        ctMethod.setBody(body.toString());
//        ctClass.addMethod(ctMethod);
//        Class clazz = ctClass.toClass();
//        Object o = clazz.newInstance();
//        Method method = o.getClass().getMethod("execute", new Class[]{});
//        method.invoke(o, new Object[]{});
//        ctClass.writeFile();

        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(Hello.class);
        factory.setFilter(new MethodFilter() {
            @Override
            public boolean isHandled(Method m) {
                if(m.getName().equals("getName")){
                    return true;
                }
                return false;
            }
        });

        factory.setHandler(new MethodHandler() {
            @Override
            public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
                Hello hello = (Hello) self;
                System.out.println("proxy");
                hello.say();
                return proceed.invoke(self,args);
            }
        });

        Class c = factory.createClass();
        Hello obj = (Hello) c.newInstance();
        obj.say();
    }

    class SimpleLoader extends ClassLoader {
        private ClassPool pool;

        public SimpleLoader() throws NotFoundException {
            pool = new ClassPool();
            pool.insertClassPath("./class");
        }

        protected Class findClass(String name) {
            try {
                CtClass cc = pool.get(name);
                byte[] b = cc.toBytecode();
                return defineClass(name, b, 0, b.length);
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (CannotCompileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class MyTranslator implements Translator {

        @Override
        public void start(ClassPool pool) throws NotFoundException, CannotCompileException {

        }

        @Override
        public void onLoad(ClassPool pool, String classname) throws NotFoundException, CannotCompileException {
            CtClass cc = pool.get(classname);
            cc.setModifiers(Modifier.PUBLIC);
        }
    }


}
