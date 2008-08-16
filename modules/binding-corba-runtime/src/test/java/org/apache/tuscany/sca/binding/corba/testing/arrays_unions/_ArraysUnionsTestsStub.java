package org.apache.tuscany.sca.binding.corba.testing.arrays_unions;


/**
* org/apache/tuscany/sca/binding/corba/testing/arrays_unions/_ArraysUnionsTestsStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from arrays_unions.idl
* sobota, 16 sierpie� 2008 00:51:16 CEST
*/

public class _ArraysUnionsTestsStub extends org.omg.CORBA.portable.ObjectImpl implements org.apache.tuscany.sca.binding.corba.testing.arrays_unions.ArraysUnionsTests
{

  public org.apache.tuscany.sca.binding.corba.testing.arrays_unions.TestStruct passTestStruct (org.apache.tuscany.sca.binding.corba.testing.arrays_unions.TestStruct arg)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("passTestStruct", true);
                org.apache.tuscany.sca.binding.corba.testing.arrays_unions.TestStructHelper.write ($out, arg);
                $in = _invoke ($out);
                org.apache.tuscany.sca.binding.corba.testing.arrays_unions.TestStruct $result = org.apache.tuscany.sca.binding.corba.testing.arrays_unions.TestStructHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return passTestStruct (arg        );
            } finally {
                _releaseReply ($in);
            }
  } // passTestStruct

  public String[][] passStringArray (String[][] arg)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("passStringArray", true);
                org.apache.tuscany.sca.binding.corba.testing.arrays_unions.StringArrayHelper.write ($out, arg);
                $in = _invoke ($out);
                String $result[][] = org.apache.tuscany.sca.binding.corba.testing.arrays_unions.StringArrayHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return passStringArray (arg        );
            } finally {
                _releaseReply ($in);
            }
  } // passStringArray

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:org/apache/tuscany/sca/binding/corba/testing/arrays_unions/ArraysUnionsTests:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
     s.writeUTF (str);
  }
} // class _ArraysUnionsTestsStub
