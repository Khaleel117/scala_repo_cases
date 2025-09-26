class Test {
   def bad1(){
      val color = "blue"
      val strings = List("blue", "bob")
      // {fact rule=improper-validation-of-array-index@v1.0 defects=1}
      // ruleid: positive-number-index-of
      if(strings.indexOf(color) > 0){
         println("This is if statement");
      }
      // {/fact}
   }

   def bad2(){
      val name = "bob"
      // {fact rule=improper-validation-of-array-index@v1.0 defects=1}
      // ruleid: positive-number-index-of
      if(name.indexOf("b") > 2){
         println("This is if statement");
      }
      // {/fact}
   }

   def ok1() {
      val color = "blue"
      val strings = List("blue", "bob")
      // {fact rule=improper-validation-of-array-index@v1.0 defects=0}
      // ok: positive-number-index-of
      if(strings.indexOf(color) > -1){
         println("This is if statement");
      }
      // {/fact}
   }

   def ok2(){
      val name = "bob"
      // {fact rule=improper-validation-of-array-index@v1.0 defects=0}
      // ok: positive-number-index-of
      if(name.indexOf("b") >= 0){
         println("This is if statement");
      }
      // {/fact}
   }
}
