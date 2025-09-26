import slick.jdbc.H2Profile.api._

class FooBar {
  def something(name: String) = {
    val db = Database.forConfig("h2mem1")

    lazy val people = TableQuery[People]
    // {fact rule=sql-injection@v1.0 defects=0}
    // ok: scala-slick-overrideSql-literal
    people.map(p => (p.id,p.name,p.age))
      .result
      .overrideSql("SELECT id, name, age FROM Person")
    // {/fact}
    
    val query = "SELECT id, name, age FROM Person"
    // {fact rule=sql-injection@v1.0 defects=0}
    // ok: scala-slick-overrideSql-literal
    people.map(p => (p.id,p.name,p.age))
      .result
      .overrideSql(query)
    // {/fact}

    // {fact rule=sql-injection@v1.0 defects=1}
    // ruleid: scala-slick-overrideSql-literal
    people.map(p => (p.id,p.name,p.age))
      .result
      .overrideSql(s"SELECT id, name, age FROM Person WHERE $name")
    // {/fact}
  }
}