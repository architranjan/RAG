function UploadPdf({ file, setFile, uploadPdf }) {
  return (
    <div className="upload-box">
      <input
        type="file"
        accept="application/pdf"
        onChange={(e) => setFile(e.target.files[0])}
      />
      <button onClick={uploadPdf}>Upload PDF</button>
    </div>
  );
}

export default UploadPdf;
