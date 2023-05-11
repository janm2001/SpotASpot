import "@/styles/globals.css";
import Head from "next/head";
import favicon from "../public/favicon/favicon-16x16.png";

import Layout from "@/components/Layout/Layout";

export default function MyApp({ Component, pageProps }) {
  return (
    <>
      <Layout>
        <Component className="background" {...pageProps} />
      </Layout>
    </>
  );
}
