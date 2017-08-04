// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.mathlang.impl;

import java.io.IOException;

import com.google.codeu.mathlang.core.tokens.NameToken;
import com.google.codeu.mathlang.core.tokens.NumberToken;
import com.google.codeu.mathlang.core.tokens.StringToken;
import com.google.codeu.mathlang.core.tokens.SymbolToken;
import com.google.codeu.mathlang.core.tokens.Token;
import com.google.codeu.mathlang.parsing.TokenReader;

// MY TOKEN READER
//
// This is YOUR implementation of the token reader interface. To know how
// it should work, read src/com/google/codeu/mathlang/parsing/TokenReader.java.
// You should not need to change any other files to get your token reader to
// work with the test of the system.
public final class MyTokenReader implements TokenReader {

  public final String source;
  
  public MyTokenReader(String source) {
    // Your token reader will only be given a string for input. The string will
    // contain the whole source (0 or more lines).
	this.source = source;
  }

  @Override
  public Token next() throws IOException {
    // Most of your work will take place here. For every call to |next| you should
    // return a token until you reach the end. When there are no more tokens, you
    // should return |null| to signal the end of input.

    // If for any reason you detect an error in the input, you may throw an IOException
    // which will stop all execution.

	// trim source string so that there is no leading or trailing whitespace
	String trimmedSource = source.trim();
	String tokenString = "";
	int endIndexOfToken = 0;
	StringToken nullToken = null;
	
	// check if source contains any tokens
    if(source.length()==0) {
      return nullToken;
    }
    // check if source contains more than one token
    else if(trimmedSource.contains(" ")) {
      endIndexOfToken = trimmedSource.indexOf(" ");
      
      // get the token in form of a string
      tokenString = trimmedSource.substring(0, endIndexOfToken);
      
    // case 1: token is a symbol
    if(isSymbol(tokenString)) {
      return new SymbolToken(tokenString.charAt(0));
    }
    // case 2: token is a number
    else if(isNumber(tokenString)) {
      return new NumberToken(Double.parseDouble(tokenString));
    }
    // case 3: token is a name
    else if(isName(tokenString)) {
      return new NameToken(tokenString);
    }
    // case 4: token is a string
    else {
      return new StringToken(tokenString);
    }
    
    }
    
    return nullToken;
    
  }
  
  // case 1: check if token is symbol
  
  public boolean isSymbol(String tokenString) {
	if(tokenString.length() == 1) {
		return true;
	}
	return false;
  }
  
  //case 2: check if token is number
  
  public boolean isNumber(String tokenString) {
	try{
	  Double isTokenNumber = Double.parseDouble(tokenString);
	  return true;
	} catch(NumberFormatException e) {
	  return false;
	}
  }
 
  //case 3: check if token is name
 
  public boolean isName(String tokenString) {
	return Character.isLetter(tokenString.charAt(0));
  }
}
