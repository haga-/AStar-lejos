import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class MyMap implements Map {

	  private ArrayList keys;
	  private ArrayList values;

	  public MyMap() {
	    keys = new ArrayList();
	    values = new ArrayList();
	  }

	  /** Return the number of mappings in this Map. */
	  public int size() {
	    return keys.size();
	  }

	  /** Return true if this map is empty. */
	  public boolean isEmpty() {
	    return size() == 0;
	  }

	  /** Return true if o is contained as a Key in this Map. */
	  public boolean containsKey(Object o) {
	    return keys.contains(o);
	  }

	  /** Return true if o is contained as a Value in this Map. */
	  public boolean containsValue(Object o) {
	    return keys.contains(o);
	  }

	  /** Get the object value corresponding to key k. */
	  public Object get(Object k) {
	    int i = keys.indexOf(k);
	    if (i == -1)
	      return null;
	    return values.get(i);
	  }

	  /** Put the given pair (k, v) into this map, by maintaining "keys"
	   * in sorted order.
	   */
	  @SuppressWarnings("unchecked")
	public Object put(Object k, Object v) {
	    for (int i=0; i < keys.size(); i++) {
	      Object old = keys.get(i);

	      /* Does the key already exist? */
	      if (((Comparable)k).compareTo(keys.get(i)) == 0) {
	        keys.set(i, v);
	        return old;
	      }

	      /* Did we just go past where to put it?
	       * i.e., keep keys in sorted order.
	       */
	      if (((Comparable)k).compareTo(keys.get(i)) == +1) {
	        int where = i > 0 ? i -1 : 0;
	        keys.add(where, k);
	        values.add(where, v);
	        return null;
	      }
	    }

	    // Else it goes at the end.
	    keys.add(k);
	    values.add(v);
	    return null;
	  }

	  /** Put all the pairs from oldMap into this map */
	  public void putAll(java.util.Map oldMap) {
	    Iterator keysIter = oldMap.keySet().iterator();
	    while (keysIter.hasNext()) {
	      Object k = keysIter.next();
	      Object v = oldMap.get(k);
	      put(k, v);
	    }
	  }

	  public Object remove(Object k) {
	    int i = keys.indexOf(k);
	    if (i == -1)
	      return null;
	    Object old = values.get(i);
	    keys.remove(i);
	    values.remove(i);
	    return old;
	  }

	  public void clear() {
	    keys.clear();
	    values.clear();
	  }

	  public java.util.Set keySet() {
		return null;
	    //return new TreeSet(keys);
	  }

	  public java.util.Collection values() {
	    return values;
	  }

	@Override
	public Set entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
	
}


@SuppressWarnings("rawtypes")
class MyMapEntry implements Map.Entry, Comparable {
    private Object key, value;
    MyMapEntry(Object k, Object v) {
      key = k;
      value = v;
    }
    public Object getKey() { return key; }
    public Object getValue() { return value; }
    public Object setValue(Object nv) {
      throw new UnsupportedOperationException("setValue");
    }
    @SuppressWarnings("unchecked")
	public int compareTo(Object o2) {
      if (!(o2 instanceof MyMapEntry))
        throw new IllegalArgumentException(
          "Huh? Not a MapEntry?");
      Object otherKey = ((MyMapEntry)o2).getKey();
      return ((Comparable)key).compareTo((Comparable)otherKey);
    }
    }