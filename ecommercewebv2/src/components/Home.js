import { useContext, useEffect, useState } from "react";
import { Alert, Button, Card, Col, Row, Spinner } from "react-bootstrap";
import Apis, { endpoints } from "../configs/Apis";
import { useSearchParams } from "react-router-dom";
import MySpinner from "./layout/MySpinner";
import cookie from 'react-cookies'
import { MyCartContext } from "../configs/Contexts";

const Home = () => {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [page, setPage] = useState(1);
    const [q] = useSearchParams();
    const [cart, cartDispatch] = useContext(MyCartContext);

    const loadProducts = async () => {
        try {
            setLoading(true);

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
    
    const addToCart = (p) => {
        let cart = cookie.load("cart") || {};

        if (p.id in cart) {
            cart[p.id]['quantity']++;
        } else {
            cart[p.id] = {
                "id": p.id,
                "name": p.name,
                "price": p.price,
                "quantity": 1
            }
        }

        cookie.save('cart', cart);
        console.info(cart);

        cartDispatch({
            "type": "update"
        })
    }

    return (
        <>
            {loading && <MySpinner />}

            {products.length === 0 && <Alert variant="info" className="mt-2">Không có sản phẩm nào!</Alert>}

            <Row>
                {products.map(p => <Col className="p-2" md={3} xs={6} key={p.id}>
                    <Card>
                        <Card.Img variant="top" src={p.image} />
                        <Card.Body>
                            <Card.Title>{p.name}</Card.Title>
                            <Card.Text>{p.price.toLocaleString()} VNĐ</Card.Text>
                            <Button className="me-1" variant="primary">Xem chi tiết</Button>
                            <Button onClick={() => addToCart(p)} variant="danger">Đặt hàng</Button>
                        </Card.Body>
                    </Card>
                </Col>)}
            </Row>

            {page > 0 && <div className="text-center mb-2">
                <Button variant="info" onClick={loadMore}>Xem thêm...</Button>
            </div>}
            
        </>
    );
}

export default Home;