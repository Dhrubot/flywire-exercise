const Footer = () => {
  return (
    <footer className="footer footer-center bg-primaryFont text-base-content p-4">
      <aside>
        <p className="text-white text-center">
          Copyright © <span className="text-secondaryBg">{new Date().getFullYear()} </span>- All right reserved by <span className="text-secondaryColor">Flywrite</span>
        </p>
      </aside>
    </footer>
  )
};

export default Footer;
