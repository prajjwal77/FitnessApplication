package com.FitnessApplication.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MotivationalContent {

	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String quote;
	    private String videoUrl;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getQuote() {
			return quote;
		}
		public void setQuote(String quote) {
			this.quote = quote;
		}
		public String getVideoUrl() {
			return videoUrl;
		}
		public void setVideoUrl(String videoUrl) {
			this.videoUrl = videoUrl;
		}
	    
	    
}
