package br.com.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private Twitter twitter = TwitterFactory.getSingleton();
	private QueryResult result;
	
	@CrossOrigin(origins = "http://localhost:5000")
	@RequestMapping("/twitter")
	public List<String> greeting(@RequestParam(value = "tweet") String tweet) {
		List<Tweets> tweets = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime since = now.minusDays(1);
		LocalDateTime until = now.minusDays(1 - 1);
		
		Query query = new Query("#"+tweet);
		
		query.count(5);
		query.setSince(since.format(DATE_PATTERN));
		query.setUntil(until.format(DATE_PATTERN));
		
		try {
//			do {
				result = twitter.search(query);
				for (Status status : result.getTweets()) {
					Tweets tweetResult = new Tweets();

					tweetResult.setId(status.getId());
					tweetResult.setNome(status.getUser().getName());
					tweetResult.setData(status.getCreatedAt());
					tweetResult.setTweet(status.getText());
					tweetResult.setRetweet(status.isRetweet());
					tweetResult.setFavorited(status.isFavorited());

					tweets.add(tweetResult);
				}

//			} while (result.hasNext());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return tweets.stream().map(s -> s.getTweet()).collect(Collectors.toList());
	}
}
