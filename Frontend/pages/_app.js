import '@/styles/globals.css'


import Layout from '@/components/Layout/Layout'


export default function MyApp({ Component, pageProps }) {
 
  return (
    <Layout>
      <Component className="background" {...pageProps} />
    </Layout>
  )
}