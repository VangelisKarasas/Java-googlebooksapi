package model;

public class BookInfo {
			private final String kind;
			private final String id;
			private final String etag;
			private final String selfLink;
			private final String volumeInfo;
			
			public BookInfo(String kind,String id,String etag,String selfLink,String volumeInfo) {
				this.kind = kind;
				this.id = id;
				this.etag = etag;
				this.selfLink = selfLink;
				this.volumeInfo = volumeInfo;
			}
			public String getKind() {
				return kind;
			}
			
			public String getId() {
				return id;
			}
			
			public String getEtag() {
				return etag;
			}
			
			public String getSelfLink() {
				return selfLink;
			}
			
			public String getVolumeInfo() {
				return volumeInfo;
			}
			
			public String toString() {
				return "BookVolume{" +
						"Kind='" +kind+"'\n"+
						",id='" +id +"'\n"+
						",etag='" +etag +"'\n"+
						",selfLink='" + selfLink +"'\n"+
						",volumeInfo='" +volumeInfo +"'\n"+
						'}';
			}




}
