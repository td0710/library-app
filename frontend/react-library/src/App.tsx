import React from "react";
import { Navbar } from "./layouts/NavbarAndFooter/Navbar";
import { ExploreTopBooks } from "./layouts/HomePage/components/ExploreTopBooks";
import { Carousel } from "./layouts/HomePage/components/Carousel";
import "./App.css";
import { Heros } from "./layouts/HomePage/components/Heros";
import { Footer } from "./layouts/NavbarAndFooter/Footer";
import { HomePage } from "./layouts/HomePage/HomePage";
import { SearchBooksPage } from "./layouts/NewBooksPage/NewBooksPage";
import { Route } from "react-router-dom";
import { Redirect } from "react-router-dom";
import { BookCheckoutPage } from "./BookCheckoutPage/BookCheckoutPage";
import { Switch } from "react-router-dom";
import { oktaConfig } from "./lib/oktaConfig";
import { OktaAuth, toRelativeUrl } from "@okta/okta-auth-js";
import { LoginCallback, SecureRoute, Security } from "@okta/okta-react";
import LoginWidget from "./Auth/LoginWidget";
import { useHistory } from "react-router-dom";
import { ReviewListPage } from "./BookCheckoutPage/ReviewListPage/ReviewListPage";
import { ShelfPage } from "./ShelfPage/ShelfPage";
import { MessagePage } from "./layouts/MessagesPage/MessagePage";

const oktaAuth = new OktaAuth(oktaConfig);

export const App = () => {
  const customAuthHandler = () => {
    history.push("/login");
  };

  const history = useHistory();

  const restoreOriginalUri = async (_oktaAuth: any, originalUri: any) => {
    history.replace(toRelativeUrl(originalUri || "/", window.location.origin));
  };

  return (
    <div className="d-flex flex-column min-vh-100">
      <Security
        oktaAuth={oktaAuth}
        restoreOriginalUri={restoreOriginalUri}
        onAuthRequired={customAuthHandler}
      >
        <Navbar />
        <div className="flex-grow-1">
          <Switch>
            <Route path="/" exact>
              <Redirect to="/home" />
            </Route>
            <Route path="/home">
              <HomePage />
            </Route>
            <Route path="/search">
              <SearchBooksPage />
            </Route>
            <Route path="/reviewlist/:bookId">
              <ReviewListPage></ReviewListPage>
            </Route>
            <Route path="/reviewlist/:bookId">
              <BookCheckoutPage />
            </Route>
            <Route path="/checkout/:bookId">
              <BookCheckoutPage />
            </Route>
            <Route
              path="/login"
              render={() => <LoginWidget config={oktaConfig} />}
            />
            <Route path="/login/callback" component={LoginCallback} />
            <SecureRoute path="/shelf">
              <ShelfPage></ShelfPage>
            </SecureRoute>
            <SecureRoute path="/messages">
              <MessagePage></MessagePage>
            </SecureRoute>
          </Switch>
        </div>
        <Footer />
      </Security>
    </div>
  );
};
