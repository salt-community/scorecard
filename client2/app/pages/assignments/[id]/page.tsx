import { useRouter } from "next/router";

export default function AssignmentPage(){

    const router = useRouter();
    return (
        <>
        <p>
            {router.query.id}
        </p>
        </>
    )
}