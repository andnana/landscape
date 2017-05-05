xquery version "1.0" encoding "utf-8";
declare namespace saxon="http://saxon.sf.net/";
declare variable $en_name as xs:string external;
declare option saxon:output "encoding=gbk";
for $region in doc("http://127.0.0.1:8080/region/region.xml")/regions/region
where $region/en_name eq $en_name
return $region