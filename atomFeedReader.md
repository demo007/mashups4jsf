This component gives you the ability to get any ATOM Feed in your JSF page.

| Component Attribute   | Required | Description | Default value |
|:----------------------|:---------|:------------|:--------------|
| feedURL | true | The ATOM feed URL. | NA |
| maximumCount | true | The maximum feed count. | NA |
| feedVar | true | The variable using which you will be able to access the ATOM feed. | NA |
| entryVar | true | The variable using which you will be able to access the Atom entry. Note that the returned entry is of type (com.sun.syndication.feed.synd.SyndEntry). | NA |
| entryIndex | true | The Atom entry index. | NA |
| includeCustomModules | false | If set to true then custom modules will be accessible as JSF EL. | false |