package com.example.hemtest;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
       * Create a map and add all words then
       * check if there are are any dublets or more
       */
      Map<String, Integer> tmp = new TreeMap<>();        

      String word_arr[] = str.split(" ");
      
      for (int i = 0; i < word_arr.length; i++) {
        // Add +1 if word occures more then one
        if (tmp.containsKey(word_arr[i])) {
          tmp.put(word_arr[i], tmp.get(word_arr[i])+1);
          
        } else {
          tmp.put(word_arr[i], 1);
        }
      }

      return tmp;
    
    }
} // end class HemtestController
