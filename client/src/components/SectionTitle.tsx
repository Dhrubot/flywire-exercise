const SectionTitle = ({ title }: {title: string}) => {
    return (
      <div className="divider pb-20">
          <h1 className="text-5xl text-center text-primaryFont">
              {title}
          </h1>
        </div>
    )
  };
  
  export default SectionTitle;