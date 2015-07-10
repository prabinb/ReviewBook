package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.ProductCategories;
import model.User;
import model.UserReviews;

import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import play.mvc.Controller;
import play.mvc.Result;

import com.avaje.ebean.Ebean;
import com.google.common.collect.Lists;

public class LoadData extends Controller {

  private static String USER_AGENT = "Mozilla/5.0";
  private static int fileCount = 1;
  private static String fileLocation = "/home/prabinb/workspace_java_8/XMLParser/data/xml";
  private static int productIdCount = 1;

  public Result LoadReviewData() throws IOException {
    /*
     * List<String> listOfLinks = parseFlipkartSitesXML(); for (String url : listOfLinks) {
     * sendGet(url, ".xml.gz"); }
     */


    Files.walk(Paths.get(fileLocation)).forEach(filePath -> {
      if (Files.isRegularFile(filePath)) {
        /* System.out.println(filePath); */
        List<String> productURLs = parseFlipkartProductListXML(filePath.toString());
        productURLs.forEach(productURL -> {
          try {
            readReviews(productURL);
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
      }
    });

    // readReviews("http://www.flipkart.com/karbonn-titanium-s2/p/itmdkpk5umkfuhfm");
    return ok();
  }

  private static List<String> parseFlipkartProductListXML(String filePath) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    List<String> result = new ArrayList<String>();
    try {
      builder = factory.newDocumentBuilder();
      Document document;
      document = ((DocumentBuilder) builder).parse(new FileInputStream(filePath));
      Element docEle = document.getDocumentElement();
      docEle.normalize();
      System.out.println("Root Element:" + docEle.getNodeName());
      NodeList nodeList = document.getElementsByTagName("loc");

      for (int i = 0; i < nodeList.getLength(); i++) {
        Node n = (Node) nodeList.item(i);
        System.out.println("Node Name: " + n.getNodeName());
        System.out.println("Node text: " + n.getTextContent());
        result.add(n.getTextContent());
      }
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  private void readReviews(String url) throws IOException {

    org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
    String[] data = url.split("/");
    System.out.println("Product Name: " + data[3]);
    String productName = data[3].trim().replace('-', ' ');
    // System.out.println(url.substring(url.indexOf("/") + 1, url.indexOf("/", url.indexOf("/") +
    // 1)));
    org.jsoup.select.Elements scripts = doc.select("script");
    String category = "default";
    for (org.jsoup.nodes.Element script : scripts) {
      Pattern p = Pattern.compile("FKART.analytics\\[\"category\"] = \"?[a-zA-Z0-9]+\"");
      Matcher m = p.matcher(script.html()); // you have to use html here
                                            // and NOT text! Text will
      while (m.find()) {
        String matchedString = m.group();

        category =
            matchedString
                .substring(matchedString.indexOf("=") + 3, matchedString.lastIndexOf("\""));

        System.out.println(category);
      }
    }

    List<String> titleList = Lists.newLinkedList();
    org.jsoup.select.Elements reviewTitles = doc.getElementsByClass("review-title");
    for (org.jsoup.nodes.Element reviewTitle : reviewTitles) {
      System.out.println("Title : " + reviewTitle.text());
      titleList.add(reviewTitle.text());
    }

    List<String> textList = Lists.newLinkedList();
    org.jsoup.select.Elements reviewTexts = doc.getElementsByClass("review-text");
    for (org.jsoup.nodes.Element reviewText : reviewTexts) {
      System.out.println("Comment : " + reviewText.text());
      textList.add(reviewText.text());
    }

    List<String> userList = Lists.newLinkedList();
    org.jsoup.select.Elements reviewUserNames = doc.getElementsByClass("review-userName");
    for (org.jsoup.nodes.Element reviewUserName : reviewUserNames) {
      System.out.println("User Name : " + reviewUserName.text());
      userList.add(reviewUserName.text());
    }

    for (int i = 0; i < titleList.size(); i++) {
      User user = null;
      ProductCategories productCategory = null;
      UserReviews userReviews = new UserReviews();
      // ProductCategories productCategory = new ProductCategories();
      productCategory =
          Ebean.find(ProductCategories.class).where().eq("name", category).findUnique();
      if (productCategory == null) {
        productCategory = new ProductCategories();
        productCategory.setName(category);
      }

      userReviews.setProductName(productName);
      userReviews.setProductCategory(productCategory);
      userReviews.setReviewTitle(titleList.get(i));
      userReviews.setReviewContent(textList.get(i));
      String emailId = userList.get(i).replace(' ', '.').concat("@gmail.com");

      user = Ebean.find(User.class).where().eq("email_id", emailId).findUnique();
      if (user == null) {
        user = new User();
        user.setEmailId(emailId);
        user.setFullname(userList.get(i));
      }

      FileInputStream fileInputStream = null;
      byte[] bFile =
          new byte[(int) new File(
              "/home/prabinb/hackathon_project/activator-1.3.5-minimal/ReviewBook/data/dummy_receipt.jpg")
              .length()];

      try {
        fileInputStream =
            new FileInputStream(
                "/home/prabinb/hackathon_project/activator-1.3.5-minimal/ReviewBook/data/dummy_receipt.jpg");
        fileInputStream.read(bFile);
        fileInputStream.close();

      } catch (Exception ex) {

      }
      userReviews.setImageData(bFile);
      userReviews.setImageType(FilenameUtils.getExtension("dummy_receipt.jpg"));
      userReviews.setPostedDate(new Date());
      userReviews.setUser(user);
      // user.save();
      userReviews.save();
    }
  }
}
