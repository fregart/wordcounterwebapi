package com.example.hemtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.*;

import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for Hemtest
 *
 * @author Fredrik Edman (Fregart)
 *
 */
@ResponseBody
@Controller
public class HemtestController {
  
  @RequestMapping(value = "/test", method = RequestMethod.GET)
		public String hello(){
      return "Hey, this is Hemtest";
    }

  @PostMapping("/count")
    public Map<String, Integer> countBody(@RequestBody String str) {

      /**
       * Check if the incoming string matches the pattern
       */
      Pattern pat = Pattern.compile("[A-ZÅÄÖa-zåäö\s]+");
      Matcher match = pat.matcher(str);
      Boolean stringOk = match.matches();

      if (stringOk) {
        // Split the string into an array of words
        String word_arr[] = str.split(" ");
        
        /**
         * Capitalize the first character of each
         * word in the string to prevent duplicates of the same
         * word with different case
         */
        for (int i = 0; i < word_arr.length; i++) {
          {
            word_arr[i] = word_arr[i].substring(0, 1).toUpperCase() + word_arr[i].substring(1).toLowerCase();
          }
        }

        /**
         * Create a map and add all words then
         * check if there are are any dublets or more
         */
        Map<String, Integer> listOfWords = new HashMap<>();        
        
        for (int i = 0; i < word_arr.length; i++) {
          // Add +1 if word occures more then one time
          if (listOfWords.containsKey(word_arr[i])) {
            listOfWords.put(word_arr[i], listOfWords.get(word_arr[i])+1);
            
          } else {
            listOfWords.put(word_arr[i], 1);
          }
        }

        // Sort the list in descending order by value
        List<Map.Entry<String, Integer>> wordEntries = new ArrayList<>(listOfWords.entrySet());
          wordEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // Pick out the top 10 words from wordEntries list
        Map<String, Integer> listOfWordsResult = new LinkedHashMap<>();
        for (int i = 0; i < 10 && i < wordEntries.size(); i++) {
          listOfWordsResult.put(wordEntries.get(i).getKey(), wordEntries.get(i).getValue());
        }

        // Return top 10 list of words
        return listOfWordsResult;

      }else{
        // Return 400 error message if the string don't match the pattern
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
    }
} // end class HemtestController
