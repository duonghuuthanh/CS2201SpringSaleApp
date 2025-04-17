import { useEffect, useState } from "react";
import { Alert, Button, Card, Col, Row, Spinner } from "react-bootstrap";
import Apis, { endpoints } from "../configs/Apis";
import { useSearchParams } from "react-router-dom";
import MySpinner from "./layouts/MySpinner";

const Home = () => {
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(1);
    const [loading, setLoading] = useState(false);
    const [q] = useSearchParams();

    const loadProducts = async () => {
        try {
            setLoading(true)

            let url = `${endpoints['products']}?page=${page}`;

            let cateId = q.get('cateId');
            if (cateId) {
                url = `${url}&categoryId=${cateId}`;
            }
            
            let kw = q.get('kw');
            if (kw) {
                url = `${url}&kw=${kw}`;
            }

            let res = await Apis.get(url);
            if (res.data.length === 0)
                setPage(0);
            else {
                if (page === 1)
                    setProducts(res.data);
                else
                    setProducts([...products, ...res.data]);
            }
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        if (page > 0) 
            loadProducts();
    }, [page, q]);

    useEffect(() => {
        setPage(1);
        setProducts([]);
    }, [q]);

    const loadMore = () => {
        if (!loading && page > 0)
            setPage(page + 1);
    }

    return (
        <>
            {products.length === 0 && <Alert variant="info" className="m-2">Không có sản phẩm nào!</Alert>}
            <Row>
                {products.map(p => <Col className="p-2" key={p.id} md={3} xs={6}>
                    <Card>
                        <Card.Img variant="top" src={p.image} />
                        <Card.Body>
                            <Card.Title>{p.name}</Card.Title>
                            <Card.Text>{p.price.toLocaleString()} VNĐ</Card.Text>
                            <Button className="me-1" variant="primary">Xem chi tiết</Button>
                            <Button variant="danger">Đặt hàng</Button>
                        </Card.Body>
                    </Card>
                </Col>)}
            </Row>
            {page > 0 && <div className="text-center">
                <Button className="btn btn-primary mt-1 mb-1" onClick={loadMore}>Xem thêm...</Button>
            </div>}
            
            {loading &&  <MySpinner />}
        </>
    )
}

export default Home;