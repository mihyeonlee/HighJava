package kr.or.ddit.behavioral.iterator;

public class NameRepository implements Container{
	
	public String names[] = {"홍길동", "이순신", "강감찬", "무지","막지"};
	
	
	public Iterator getIterator() {
		return new NameIterator();
	};
	
	private class NameIterator implements Iterator{
		int index;

		@Override
		public boolean hasNext() {
			if(index < names.length) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if(this.hasNext()) {
				return names[index++];
			}
			return null;
		}
	}
}
