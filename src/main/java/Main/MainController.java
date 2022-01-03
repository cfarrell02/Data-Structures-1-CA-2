package Main;

import Models.AuctionLot;
import Models.Bidder;
import Utils.AlertBox;
import Utils.CoolLinkedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;
import java.util.Locale;


public class MainController {
    private CoolLinkedList<Bidder> sortedBidder = new CoolLinkedList<>();
    private CoolLinkedList<AuctionLot> sortedAuctionLot = new CoolLinkedList<>();
    @FXML
    private TextField searchBar;

    @FXML
    private ListView frontList;

    private static String currentTitle;

    public void showBidder() throws IOException {
        currentTitle = "Bidders";
        Stage stage = AuctionApplication.mainWindow;
        FXMLLoader fxmlLoader = new FXMLLoader(AuctionApplication.class.getResource("model-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(currentTitle);
        stage.setScene(scene);
        stage.show();
    }

    public void showAuctionLot() throws IOException {
        currentTitle = "Auction Lots";
        Stage stage = AuctionApplication.mainWindow;
        FXMLLoader fxmlLoader = new FXMLLoader(AuctionApplication.class.getResource("model-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(currentTitle);
        stage.setScene(scene);
        stage.show();
    }

    public static String getCurrentTitle() {
        return currentTitle;
    }

    public void search() {
        try {
            String searchValue = searchBar.getText().toLowerCase(Locale.ROOT).trim();
            frontList.getItems().clear();
//            CoolLinkedList sortedBidder = AuctionApplication.getAuctionAPI().getBidders().toCoolLinkedList();
//            AuctionApplication.getAuctionAPI().getBidders().toCoolLinkedList().mergeSort(Comparator.comparing(Bidder::getName));
//            sortedBidder.mergeSort(Comparator.comparing(Bidder::getName));
//             System.out.println(sortedBidder);


            for (Bidder bidder : AuctionApplication.getAuctionAPI().getBidders().toCoolLinkedList())
                if (bidder.getName().toLowerCase(Locale.ROOT).trim().contains(searchValue) || bidder.getTelephone().toLowerCase(Locale.ROOT).trim().contains(searchValue) ||
                        bidder.getAddress().toLowerCase(Locale.ROOT).trim().contains(searchValue) || bidder.getEmail().toLowerCase(Locale.ROOT).trim().contains(searchValue)) {

                    sortedBidder.add(bidder);
                    sortedBidder.mergeSort(Comparator.comparing(Bidder::getName));
                }
            for (Bidder bidder1 : sortedBidder) {

                frontList.getItems().add(bidder1.toString());

            }

            if (AuctionApplication.getAuctionAPI().getAuctionLots().size() > 0) {
                for (AuctionLot auctionLot : AuctionApplication.getAuctionAPI().getAuctionLots().toCoolLinkedList()) {
                    if (auctionLot.getTitle().toLowerCase(Locale.ROOT).trim().contains(searchValue) || auctionLot.getDescription().toLowerCase(Locale.ROOT).trim().contains(searchValue)
                            || auctionLot.getType().toLowerCase(Locale.ROOT).trim().contains(searchValue)) {
                        sortedAuctionLot.add(auctionLot);
                        sortedAuctionLot.mergeSort(Comparator.comparing(AuctionLot::getTitle));
                    }
                    for (AuctionLot auctionLot1 : sortedAuctionLot) {

                        frontList.getItems().add(auctionLot1.toString());
                    }
                }
            }

        } catch (RuntimeException e) {
            AlertBox.display("Error!", e.getMessage());

        }
    }


    public void save() throws IOException {
        AuctionApplication.save();
    }

    public void load() throws IOException, ClassNotFoundException {
        AuctionApplication.load();
    }


}